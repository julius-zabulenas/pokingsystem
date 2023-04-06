import React from 'react'
import AuthService from "../services/auth.service";

export default function HomeLoggedIn() {
    const currentUser = AuthService.getCurrentUser();
    console.log(currentUser)

    return (
        <div>
            <h2>Hello {currentUser.email}</h2>
        </div>
    )
}
