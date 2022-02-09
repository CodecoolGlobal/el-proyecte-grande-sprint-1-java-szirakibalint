import {useEffect, useState} from "react";
import Error from "../routes/Error";

function useUsers(id, onlyUsernames) {
    const [user, setUser] = useState([])
    useEffect(async () => {
        const userFromAPI = await fetchUser()
        setUser(userFromAPI)
    }, [])

    async function fetchUser() {
        let res
        let route;
        if (id === '' && !onlyUsernames) {
            route = `/api/users`;
        } else if (id === '') {
            route = `/api/usernames`;
        } else {
            route = `/api/users/` + id;
        }
        try {
            res = await fetch(route)
            return await res.json()
        } catch (e) {
            if (id === '') {
                return Error(`Error: failed to fetch user data`);
            } else {
                return Error(`Error: failed to fetch user data`);
            }
        }
    }
    return user;
}

export default useUsers;