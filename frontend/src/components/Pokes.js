import React, { useState, useEffect } from 'react'
import AuthService from "../services/auth.service";

export default function Pokes() {
    const [pokes, setPokes] = useState([]);
    const currentUser = AuthService.getCurrentUser();
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/api/poke/received/${currentUser.id}`);
            const data = await response.json();
            setPokes(data);
        }

        fetchData();
    }, []);

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch("http://localhost:8080/api/poke/users");
            const data = await response.json();
            setUsers(data);
        };

        fetchData();
    }, []);

    return (
        <>
            <h2>A list of people you have been poked by</h2>

            <div className="row">
                {pokes.map(p => {
                    return (
                        <div className="col-4" key={p.id}>
                            {users.map(u => {
                                if (u.id === p.userFromId) {
                                    return (
                                        <>
                                            <p>{u.firstName}</p>
                                            <p>{u.lastName}</p>
                                        </>
                                    )
                                }
                            })}
                            <p>{p.pokeAmount} times</p>
                        </div>
                    )
                })}
            </div>
        </>
    )
}
