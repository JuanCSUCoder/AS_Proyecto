import { getFile, getPodUrlAll, overwriteFile, saveFileInContainer } from "@inrupt/solid-client";
import {
  Session,
  getSessionFromStorage,
  getSessionIdFromStorageAll,
} from "@inrupt/solid-client-authn-node";

import cookieSession from "cookie-session";
import Cookies from "cookies";

import express from "express";
import { readFile, readFileSync } from "fs";

import cors from "cors";

const clientApplicationName = "solid-client-authn-node proyecto_as";

const app = express();
const PORT = process.env.PORT || 5006;

// This is the endpoint our NodeJS demo app listens on to receive incoming login
const REDIRECT_URL = process.env.REDIRECT_URL || `http://localhost:${PORT}/redirect`;

function addDays(date, days) {
  date.setDate(date.getDate() + days);
  return date;
};

// app.use(
//   cookieSession({
//     name: "session",
//     // These keys are required by cookie-session to sign the cookies.
//     keys: [
//       "fsbhdxbnfdxbn",
//       "ghmgfg<sfcbnhzdg",
//     ],
//     maxAge: 24 * 60 * 60 * 1000, // 24 hours
//     httpOnly: false,
//     overwrite: true,
//     expires: addDays(new Date(), 5)
//   })
// );

const cookiesOptions = {
  // keys: ["sxvsnvaiouash", "iuscbvjbaiu77483r87wedg"],
  maxAge: 24 * 60 * 60 * 1000, // 24 hours
  httpOnly: false,
  overwrite: true,
  expires: addDays(new Date(), 5)
};

const corsOptions = {
  origin: (origin, callback) => {
    callback(null, origin);
  },
  methods: "GET,HEAD,PUT,PATCH,POST,DELETE", // Métodos HTTP permitidos
  allowedHeaders: ["Content-Type", "Authorization"], // Cabeceras permitidas
  credentials: true, // Permitir cookies de origen cruzado
};

app.use(cors(corsOptions));
app.options("*", cors(corsOptions));

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
  const cookies = new Cookies(req, res, cookiesOptions);
  console.log("setting key")
  cookies.set("sessionId", session.info.sessionId)
  console.log("seted key");
  
  await session.login({
    redirectUrl: REDIRECT_URL + `/${encodeURIComponent(req.query.callback)}`,
    oidcIssuer: req.query.idp,
    clientName: clientApplicationName,
    handleRedirect: (redirectUrl) => res.redirect(redirectUrl),
  });
  console.log("logged");
  if (session.info.isLoggedIn) {
    console.log("already logged");
    res.redirect(req.query.callback + "?success=true");
  }
  console.log("reloged");
});

app.get("/redirect/:callback", async (req, res) => {
  const cookies = new Cookies(req, res, cookiesOptions);
  const sessionId = cookies.get("sessionId");
  const session = await getSessionFromStorage(sessionId);
  if (session === undefined) {
    console.log("Session not found");
    res.status(400).redirect(req.params.callback + "?success=false");
  } else {
    console.log("Session found");
    const fullUrl = getRequestFullUrl(req);
    console.log("Full URL: " + fullUrl)
    await session.handleIncomingRedirect(fullUrl);
    console.log("logging");
    if (session.info.isLoggedIn) {
      console.log("logged");
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
  console.log("end");
  res.end();
});

app.get('/session', async (req, res) => {
  const cookies = new Cookies(req, res, cookiesOptions);
  const sessionId = cookies.get("sessionId");
  const session = await getSessionFromStorage(sessionId);

  res.write(JSON.stringify(session.info));
  res.end();
});

app.get("/user", async (req, res, next) => {
  const cookies = new Cookies(req, res, cookiesOptions);
  const sessionId = cookies.get("sessionId");
  const session = await getSessionFromStorage(sessionId);
  try {
    const pods = await getPodUrlAll(session.info.webId, {
      fetch: session.fetch,
    });

    const dataURL = pods[0] + "superstore.json";

    const file = await getFile(dataURL, {
      fetch: session.fetch,
    });

    res.write(await file.text());
    res.end();
  } catch (e) {
    console.log(e);
    res.write("{}");
    res.end();
  }
});

app.put("/user", async (req, res) => {
  const cookies = new Cookies(req, res, cookiesOptions);
  const sessionId = cookies.get("sessionId");
  const session = await getSessionFromStorage(sessionId);
  try {
    if (session != undefined && session.info.isLoggedIn) {
      const pods = await getPodUrlAll(session.info.webId, {
        fetch: session.fetch,
      });

      const dataURL = pods[0] + "superstore.json";

      let data = {};
      console.log("Trying to get file")
      try {
        const file = await getFile(dataURL, {
          fetch: session.fetch,
        });
        data = JSON.parse(await file.text());
        console.log("Got file");
      } catch (error) {
        console.log("Not Found, creating ...");
        const filedata = Buffer.from(JSON.stringify({}));
        await saveFileInContainer(
          pods[0], // Container URL
          filedata, // Buffer containing file data
          {
            slug: "superstore.json",
            contentType: "application/json",
            fetch: session.fetch,
          }
        );
      }

      const mergedData = { ...data, ...req.body };

      const bufferedData = Buffer.from(JSON.stringify(mergedData));

      console.log("Got file");
      const writenFile = await overwriteFile(dataURL, bufferedData, {
        contentType: "application/json",
        fetch: session.fetch,
      });

      res.end();
    } else {
      console.log("Unauth");
      res.status(500).end();
    }
  } catch (e) {
    console.log(e);
    res.status(500).end();
  }
})

// /logout?callback=url
app.get("/logout", async (req, res, next) => {
  const cookies = new Cookies(req, res, cookiesOptions);
  const sessionId = cookies.get("sessionId");
  const session = await getSessionFromStorage(sessionId);
  if (session) {
    const { webId } = session.info;
    session.logout();
    res.redirect(req.query.callback);
  } else {
    res.status(400).redirect(req.query.callback);
  }
});

// /verify?token=dsivdsjn
app.get("/verify", async (req, res) => {
  const sessionId = req.query.token;
  const session = await getSessionFromStorage(sessionId);
  if (session) {
    if (session.info.isLoggedIn) {
      res.status(200).end();
    } else {
      res.status(401).end();
    }
  } else {
    res.status(401).end();
  }
})

// /sessionid?token=dsubfsdiu
app.get("/sessionid", async (req, res) => {
  const sessionId = req.query.token;
  const session = await getSessionFromStorage(sessionId);

  res.write(JSON.stringify(session.info));
  res.end();
});

app.listen(PORT, async () => {
  console.log(`Listening on [${PORT}]...`);
});

function getRequestFullUrl(request) {
  return `${request.protocol}://${request.get("host")}${request.originalUrl}`;
}
