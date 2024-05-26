import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import ThemeRegistry from "@/providers/ThemeRegistry";
import { NavBar } from "@/components/NavBar";

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
      <ThemeRegistry options={{ key: "joy" }}>
        <body
          className={
            inter.className + "flex flex-col items-center justify-start p-5"
          }
        >
          <h1 className="text-center mt-0">SuperStore</h1>
          <h2 className="text-center mt-0">Tu Tienda tus Datos</h2>
          <p className="text-center">
            En SuperStore no recolectamos información personal, todo se almacena
            en los pods que tu controlas!
          </p>
          <NavBar />
          {children}
        </body>
      </ThemeRegistry>
    </html>
  );
}
