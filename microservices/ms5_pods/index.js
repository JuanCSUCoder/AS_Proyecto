import {
  Session,
  getSessionFromStorage,
  getSessionIdFromStorageAll,
} from "@inrupt/solid-client-authn-node";

import cookieSession from "cookie-session";

import express from "express";

const clientApplicationName = "solid-client-authn-node proyecto_as";

const app = express();
const PORT = process.env.PORT || 5006;

// This is the endpoint our NodeJS demo app listens on to receive incoming login
const REDIRECT_URL = process.env.REDIRECT_URL || `http://localhost:${PORT}/redirect`;

app.use(
  cookieSession({
    name: "session",
    // These keys are required by cookie-session to sign the cookies.
    keys: [
      "Required, but value not relevant for this demo - key1",
      "Required, but value not relevant for this demo - key2",
    ],
    maxAge: 24 * 60 * 60 * 1000, // 24 hours
    httpOnly: false
  })
);

app.use(express.json());

app.get("/", async (req, res, next) => {
  const sessionIds = await getSessionIdFromStorageAll();
  const sessions = await Promise.all(
    sessionIds.map(async (sessionId) => {
      return getSessionFromStorage(sessionId);
    })
  );
  const htmlSessions = `${sessions.reduce((sessionList, session) => {
    if (session?.info.isLoggedIn) {
      return `${sessionList}<li><strong>${session?.info.webId}</strong></li>`;
    }
    return `${sessionList}<li>Logging in process</li>`;
  }, "<ul>")}</ul>`;
  res.send(
    `<p>There are currently [${sessionIds.length}] visitors: ${htmlSessions}</p>`
  );
});

// /login?idp=url?callback=url
app.get("/login", async (req, res, next) => {
  const session = new Session({ keepAlive: false });
  req.session.sessionId = session.info.sessionId;
  await session.login({
    redirectUrl: REDIRECT_URL + `/${encodeURIComponent(req.query.callback)}`,
    oidcIssuer: req.query.idp,
    clientName: clientApplicationName,
    handleRedirect: (redirectUrl) => res.redirect(redirectUrl),
  });
  if (session.info.isLoggedIn) {
    res.redirect(req.query.callback);
  }
});

app.get("/redirect/:callback", async (req, res) => {
  const session = await getSessionFromStorage(req.session.sessionId);
  if (session === undefined) {
    console.log("Session not found");
    res.status(400).redirect(req.params.callback + "?success=false");
  } else {
    await session.handleIncomingRedirect(getRequestFullUrl(req));
    if (session.info.isLoggedIn) {
      session.events.on("sessionExtended", () => {
        console.log("Extended session.");
      });
      console.log("Logged in successfully");
      res.redirect(req.params.callback + "?success=true");
    } else {
      console.log("Login failed");
      res.status(400).redirect(req.params.callback + "?success=false");
    }
  }
  res.end();
});

app.get("/fetch", async (req, res, next) => {
  const session = await getSessionFromStorage(req.session.sessionId);
  if (!req.query.resource) {
    res
      .status(400)
      .send(
        "<p>Expected a 'resource' query param, for example <strong>http://localhost:3001/fetch?resource=https://pod.inrupt.com/MY_USERNAME/</strong> to fetch the resource at the root of your Pod (which, by default, only <strong>you</strong> will have access to).</p>"
      );
  } else {
    const { fetch } = session ?? new Session();
    res.send(
      `<pre>${(await (await fetch(req.query.resource)).text()).replace(
        /</g,
        "&lt;"
      )}</pre>`
    );
  }
});

// /logout?callback=url
app.get("/logout", async (req, res, next) => {
  const session = await getSessionFromStorage(req.session.sessionId);
  if (session) {
    const { webId } = session.info;
    session.logout();
    res.redirect(req.query.callback);
  } else {
    res.status(400).redirect(req.query.callback);
  }
});

app.listen(PORT, async () => {
  console.log(`Listening on [${PORT}]...`);
});

function getRequestFullUrl(request) {
  return `${request.protocol}://${request.get("host")}${request.originalUrl}`;
}
