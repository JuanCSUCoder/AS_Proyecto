const test = {
  api: "http://localhost:5006",
  callback: "http://localhost:3000",
};

export function loginCallback(idp: string, callback: string) {
  window.location.href = `${test.api}/login/?idp=${encodeURIComponent(
    idp
  )}&&callback=${encodeURIComponent(test.callback + callback)}`;
}
