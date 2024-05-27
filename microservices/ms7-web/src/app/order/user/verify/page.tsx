import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";

export default function VerifyDataPage() {
  return (
    <DefaultContainer>
      <MainButton href="/order/user/modify">Modificar Datos</MainButton>
      <MainButton href="/order/shipment">Confirmar Datos</MainButton>
    </DefaultContainer>
  );
}