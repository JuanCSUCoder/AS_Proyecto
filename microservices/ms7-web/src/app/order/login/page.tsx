import { LoginForm } from "@/components/order/login/LoginForm";
import { Input } from "@mui/joy";

export default function LoginPage() {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Inicio de Sesión</h2>
      <LoginForm />
    </main>
  );
}