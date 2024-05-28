import { Pod } from "@/model/Pod";
import { useEffect, useState } from "react";

export function useUser(callback: (val: any) => void): Pod {
  const [pod, setPod] = useState<Pod>({});

  useEffect(() => {
    fetch(`${process.env.MS5_URL}/user`, {
      credentials: "include"
    })
      .then((res) => res.json())
      .then((data) => {
        setPod(data);
        callback(data)
      });
  }, []);

  return pod;
}
