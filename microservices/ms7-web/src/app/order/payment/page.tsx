import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";

export default function PaymentPage() {
  return (
    <DefaultContainer>
      <MainButton href="/order/confirm">Confirmar</MainButton>
    </DefaultContainer>
  )
}
