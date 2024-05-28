import { FormEvent } from "react";

export function extractFormObject(event: FormEvent<HTMLFormElement>) {
  const formData = new FormData(event.currentTarget);
  const formJson = Object.fromEntries((formData as any).entries());
  return formJson;
}