import { LoginForm } from "@/components/order/login/LoginForm";
import { MainButton } from "@/components/utils/MainButton";
import { Input } from "@mui/joy";

export default function LoginPage() {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Inicio de Sesión</h2>
      <LoginForm />
      <MainButton href="/order/user/verify">Ir al Proveedor de Identidad</MainButton>
    </main>
  );
}