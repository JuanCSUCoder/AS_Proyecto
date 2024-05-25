import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import ThemeRegistry from "@/providers/ThemeRegistry";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "SuperStore - Tu tienda descentralizada",
  description: "La única tienda en la que puedes comprar sin poner en riesgo tus datos personales",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="es">
      <ThemeRegistry options={{key: 'joy'}}>
        <body className={inter.className}>{children}</body>
      </ThemeRegistry>
    </html>
  );
}
