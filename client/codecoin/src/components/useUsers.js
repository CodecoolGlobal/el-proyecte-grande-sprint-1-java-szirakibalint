import {useEffect, useState} from "react";
import Error from "../routes/Error";

function useUsers(id) {
    const [user, setUser] = useState([])
    useEffect(async () => {
        const userFromAPI = await fetchUser()
        setUser(userFromAPI)
    }, [])

    async function fetchUser() {
        let res
        try {
            if (id === '') {
                res = await fetch(`/api/users`)
            } else {
                res = await fetch(`/api/users/${id}`)
            }
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