import { ReactNode } from "react";

export function DefaultContainer({children}:{children: ReactNode}) {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      <div className="flex w-full flex-col items-center justify-start md:w-3/5 gap-5">
        {children}
      </div>
    </main>
  );
}