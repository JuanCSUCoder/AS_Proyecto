import { ReactNode } from "react";

export function DefaultContainer({children}:{children: ReactNode}) {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      <div className="flex w-full flex-col items-center justify-start md:w-5/6 lg:w-4/5 gap-5">
        {children}
      </div>
    </main>
  );
}