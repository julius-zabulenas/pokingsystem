import React, { useState, useEffect } from 'react'
import AuthService from "../services/auth.service";

export default function Search() {
    const [users, setUsers] = useState({});
    const [pokes, setPokes] = useState({});
    // I use this to force a React render
    const [increment, setIncrement] = useState(false);
    const currentUser = AuthService.getCurrentUser();
    const [searchQuery, setSearchQuery] = useState("")

    // Fetching all of the users from the database, so I have something to display on 'Search for people'
    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(searchQuery !== "" ? `http://localhost:8080/api/users/search/nameStartsWith?name=${searchQuery}` : "http://localhost:8080/api/users/search/nameStartsWith?name=null");
            const data = await response.json();
            setUsers(data);
        };

        fetchData();
    }, [searchQuery]);

    // Fetching all existing or not pokes, so I can send HTTP requests to the endpoint
    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/api/pokes`);
            const data = await response.json();
            setPokes(data);
        };

        fetchData();
    }, [increment]);

    const sendPoke = async (userToId) => {
        const response = await fetch(
            "http://localhost:8080/api/pokes",
            {
                method: 'PUT',
                headers: { 'Content-Type': 'application/hal+json' },
                body: JSON.stringify({
                    "userTo": {
                        "id": userToId
                    },
                    "userFrom": {
                        "id": currentUser.id
                    },
                    "pokeAmount": 1
                })
            }
        )

        setIncrement(!increment);
        return response;

        // if (pokes?._embedded?.pokes.length === 0) {
        //     const response = await fetch(
        //         "http://localhost:8080/api/pokes",
        //         {
        //             method: 'POST',
        //             headers: { 'Content-Type': 'application/hal+json' },
        //             body: JSON.stringify({
        //                 "userTo": `http://localhost:8080/api/users/${userToId}`,
        //                 "userFrom": `http://localhost:8080/api/users/${currentUser.id}`,
        //                 "pokeAmount": 1
        //             })
        //         }
        //     )

        //     setIncrement(!increment);
        //     return response;
        // } else {
        //     pokes?._embedded?.pokes.map(poke => {
        //         const pokeAmount = poke.pokeAmount;
        //         if (poke._embedded.userFrom.id === currentUser.id && poke._embedded.userTo.id === userToId) {
        //             const response = fetch(
        //                 poke._links.self.href,
        //                 {
        //                     method: 'PUT',
        //                     headers: { 'Content-Type': 'application/hal+json' },
        //                     body: JSON.stringify({
        //                         "pokeAmount": pokeAmount + 1
        //                     })
        //                 }
        //             )

        //             setIncrement(!increment);
        //             return response;
        //         } else {
        //             const response = fetch(
        //                 "http://localhost:8080/api/pokes",
        //                 {
        //                     method: 'POST',
        //                     headers: { 'Content-Type': 'application/hal+json' },
        //                     body: JSON.stringify({
        //                         "userTo": `http://localhost:8080/api/users/${userToId}`,
        //                         "userFrom": `http://localhost:8080/api/users/${currentUser.id}`,
        //                         "pokeAmount": 1
        //                     })
        //                 }
        //             )

        //             setIncrement(!increment);
        //             return response;
        //         }
        //     })
        // }
    }

    return (
        <>
            <div className="row">
                <div className="col-4">
                    <h2>Search for people to poke:</h2>

                    <form className="d-flex">
                        <input type="text" className="form-control" placeholder="Search" onChange={e => setSearchQuery(e.target.value)} />
                    </form>
                </div>
            </div>

            <div className="row">
                {users?._embedded?.users.map(user => {
                    // Returns everyone but currently logged in user
                    if (user.id !== currentUser.id) {
                        return (
                            <div className="card col-4" style={{ width: "18rem" }} key={user.id}>
                                <img src={require(`../images/${user.image}`)} className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">{user.firstName} {user.lastName}</h5>
                                    <p className="card-text">From: {user.city}</p>
                                    <p className="card-text">Email: {user.email}</p>
                                    <button onClick={() => sendPoke(user.id)} className="btn btn-primary">Pokes: {pokes?._embedded?.pokes.map(x => {
                                        if (user.id === x._embedded.userTo.id) {
                                            return x.pokeAmount
                                        }
                                    })}</button>
                                </div>
                            </div>
                        )
                    }
                })}
            </div>
        </>
    )
}
