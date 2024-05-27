import { Button } from "@mui/joy";
import Link from "next/link";
import { ReactNode } from "react";

export function MainButton({
  href,
  type,
  children
}: {
    href?: string,
    type?: 'submit'
    children: ReactNode
}) {
  return href ? (
    <Link href={href}>
      <Button size="lg" variant="soft" color="success" type={type}>
        {children}
      </Button>
    </Link>
  ) : (
    <Button size="lg" variant="soft" color="success" type={type}>
      {children}
    </Button>
  );
}