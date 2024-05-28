/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "**",
      },
    ],
  },
  env: {
    API_KEY: process.env.API_KEY,
    SELF_URL: process.env.SELF_URL,
    MS5_URL: process.env.MS5_URL,
  },
};

export default nextConfig;
