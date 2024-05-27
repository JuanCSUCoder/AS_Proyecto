import { Button } from "@mui/joy";
import Link from "next/link";
import { ReactNode } from "react";

export function MainButton({
  href,
  type,
  loading,
  disabled,
  children
}: {
    href?: string,
    type?: 'submit',
    loading?: boolean,
    disabled?: boolean
    children: ReactNode
}) {
  return href ? (
    <Link href={href}>
      <Button
        size="lg"
        variant="soft"
        color="success"
        type={type}
        loading={loading}
      >
        {children}
      </Button>
    </Link>
  ) : (
    <Button
      size="lg"
      variant="soft"
      color="success"
      type={type}
        loading={loading}
        disabled={disabled}
    >
      {children}
    </Button>
  );
}