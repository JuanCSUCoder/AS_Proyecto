import { Button } from "@mui/joy";
import Link from "next/link";
import { ReactNode } from "react";

export function MainButton({
  href,
  children
}: {
    href: string,
    children: ReactNode
}) {
  return (
    <div className="my-5">
      <Link href={href}>
        <Button size="lg" variant="soft" color="success">
          {children}
        </Button>
      </Link>
    </div>
  );
}