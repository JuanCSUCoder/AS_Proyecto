import { DefaultContainer } from "@/components/utils/DefaultContainer";

export default function OrderDetailsPage({params}:{params:{id: string}}) {
  return (
    <DefaultContainer>
      <p>Hola { params.id }</p>
    </DefaultContainer>
  )
}