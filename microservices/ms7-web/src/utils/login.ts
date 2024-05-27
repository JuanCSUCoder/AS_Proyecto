const test = {
  api: process.env.MS5_URL,
  callback: process.env.SELF_URL,
};

export function loginCallback(idp: string, callback: string) {
  window.location.href = `${test.api}/login/?idp=${encodeURIComponent(
    idp
  )}&callback=${encodeURIComponent(test.callback + callback)}`;
}
