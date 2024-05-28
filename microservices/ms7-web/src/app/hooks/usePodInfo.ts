import { useEffect, useState } from "react";

export interface PodInfo {
  sessionId: string;
  isLoggedIn: boolean;
  webId: string;
  expirationDate: number;
}

export function usePodInfo() {
  const [podInfo, setInfo] = useState<PodInfo | undefined>(undefined);

  useEffect(() => {
    fetch("http://localhost:5006/session", {
      credentials: "include"
    }).then(res => res.json())
      .then(res => setInfo(res as PodInfo))
  }, [])

  return podInfo;
}