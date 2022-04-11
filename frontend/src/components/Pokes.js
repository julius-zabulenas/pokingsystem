import React, { useState, useEffect } from 'react'
import AuthService from "../services/auth.service";

export default function Pokes() {
    const [pokes, setPokes] = useState([]);
    const currentUser = AuthService.getCurrentUser();

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/api/users/${currentUser.id}/pokesReceived`);
            const data = await response.json();
            setPokes(data);
        }

        fetchData();
    }, []);

    return (
        <>
            <h2>A list of people you have been poked by</h2>

            <div className="row">
                {pokes?._embedded?.pokes.map(poke => {
                    return (
                        // <div className="col-4">
                        //     <p>{poke._embedded.userFrom.firstName}</p>
                        //     <p>{poke._embedded.userFrom.lastName}</p>
                        //     <p>{poke.pokeAmount} times</p>
                        // </div>
                        <div className="card col-4" style={{ width: "18rem" }}>
                            <img src={require(`../images/${poke._embedded.userFrom.image}`)} className="card-img-top" alt="..." />
                            <div className="card-body">
                                <h5 className="card-title">{poke._embedded.userFrom.firstName} {poke._embedded.userFrom.lastName}</h5>
                                <p className="card-text">From: {poke._embedded.userFrom.city}</p>
                                <p className="card-text">Email: {poke._embedded.userFrom.email}</p>
                                <p>{poke.pokeAmount} times</p>
                            </div>
                        </div>
                    )
                })}
            </div>
        </>
    )
}
