import React, { useState, useEffect } from 'react'
import AuthService from "../services/auth.service";

export default function Search() {
    const [users, setUsers] = useState([]);
    const currentUser = AuthService.getCurrentUser();
    const [pokes, setPokes] = useState([]);
    const [increment, setIncrement] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch("http://localhost:8080/api/poke/search?keyword=");
            const data = await response.json();
            setUsers(data);
        };

        fetchData();
    }, []);

    const sendPoke = async (id) => {
        setIncrement(!increment);

        return fetch(
            "http://localhost:8080/api/poke",
            {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    "userId": id,
                    "userFromId": currentUser.id,
                    "pokeAmount": 1
                })
            }
        )
    }

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/api/poke/${currentUser.id}`);
            const data = await response.json();
            setPokes(data);
        };

        fetchData();
    }, [increment]);

    return (
        <>
            <div className="row">
                <div className="col-4">
                    <h2>Search for people to poke:</h2>

                    <form className="d-flex" action="http://localhost:8080/api/poke/search?keyword=">
                        <input type="text" className="form-control" placeholder="Search" name="keyword" />
                        <button className="btn btn-primary ms-4" type="submit">Search</button>
                    </form>
                </div>
            </div>

            <div className="row">
                {users.map(user => {
                    return (
                        <div className="col-4" key={user.id}>
                            <p>{user.firstName}</p>
                            <p>{user.lastName}</p>
                            <p>{user.city}</p>
                            <p>{user.email}</p>
                            <button onClick={() => sendPoke(user.id)}>Pokes: {pokes.map(x => {
                                if (user.id === x.userId) {
                                    return x.pokeAmount
                                }
                            })}</button>
                        </div>
                    )
                })}
            </div>
        </>
    )
}
