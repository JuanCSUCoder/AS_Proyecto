import { User } from "@/model/User";
import { useEffect, useState } from "react";

export function useDBUser(webId?:string) {
  const [user, setUser] = useState<User | undefined>(undefined);

  useEffect(() => {
    fetch("http://localhost:5007/gestionusuarios/api/users/search?userPod=" + webId)
      .then(res => res.json())
      .then(res => setUser(res));
  }, [webId])

  return user;
}
