import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";

const Home = () => {
    // I can make do without the code below, I think
    // const [content, setContent] = useState("");

    // useEffect(() => {
    //     UserService.getPublicContent().then(
    //         (response) => {
    //             setContent(response.data);
    //         },
    //         (error) => {
    //             const _content =
    //                 (error.response && error.response.data) ||
    //                 error.message ||
    //                 error.toString();
    //             setContent(_content);
    //         }
    //     );
    // }, []);

    return (
        <div className="container">
            {/* <header className="jumbotron"> */}
            {/* <h3>{content}</h3> */}
            <h2>Poke-a-person</h2>
            <p>An app which lets you "poke" any person you'd like.</p>
            {/* </header> */}
        </div>
    );
};
export default Home;
