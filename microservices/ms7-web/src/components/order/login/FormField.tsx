import { Input } from "@mui/joy";

export function FormField({
  name,
}: {
  name: string
}) {
  return (
    <div className="my-3 w-full">
      <label>{name}:</label>
      <Input />
    </div>
  );
}