import { Pod } from "@/model/Pod";
import { useCallback, useEffect, useState } from "react";

export function useUser(callback: (val: any) => void): Pod | undefined {
  const [pod, setPod] = useState<Pod | undefined>(undefined);

  useEffect(() => {
    try {
      fetch(`${process.env.MS5_URL}/user`, {
        credentials: "include",
      })
        .then((res) => res.json())
        .then((data) => {
          setPod(data);
          callback(data);
        });
    } catch (error) {
      console.log(error);
    }
  }, []);

  return pod;
}
