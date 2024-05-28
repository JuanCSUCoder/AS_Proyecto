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
          headers: {
            'Content-Type': 'application/json',
          },
        })
          .then((res) => res.json())
          .then((res) => setUser(res));
      }
    }

    fetch(
      "http://localhost:5007/gestionusuarios/api/users/search?userPod=" +
      encodeURIComponent(webId as string)
    )
      .then((res) => {
        if (res.ok) {
          return res.json();
        } else {
          createUser();
        }
      })
      .then((res) => setUser(res))
      .catch((err) => createUser());
  }, [webId])

  return user;
}
