import { User } from "@/model/User";
import { useEffect, useState } from "react";

export function useDBUser(webId?:string) {
  const [user, setUser] = useState<User | undefined>(undefined);

  useEffect(() => {
    const createUser = () => {
      if (webId) {
        fetch("http://localhost:5007/gestionusuarios/api/users", {
          method: "POST",
          body: JSON.stringify({
            userPod: webId,
            providerUrl: webId,
          }),
        })
          .then((res) => res.json())
          .then((res) => setUser(res));
      }
    }

    try {
      fetch(
        "http://localhost:5007/gestionusuarios/api/users/search?userPod=" +
          webId
      )
        .then((res) => {
          if (res.ok) {
            return res.json();
          } else {
            createUser();
          }
        })
        .then((res) => setUser(res));
    } catch (error) {
      createUser();
    }
  }, [webId])

  return user;
}
