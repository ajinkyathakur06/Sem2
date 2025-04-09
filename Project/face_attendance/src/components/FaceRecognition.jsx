import { useRef, useState } from "react";
import axios from "axios";

const FaceRecognition = () => {
    const videoRef = useRef(null);
    const canvasRef = useRef(null);
    const [responseMessage, setResponseMessage] = useState("");

    const captureImage = async () => {
        if (!videoRef.current || !canvasRef.current) return;

        const canvas = canvasRef.current;
        const ctx = canvas.getContext("2d");

        canvas.width = videoRef.current.videoWidth;
        canvas.height = videoRef.current.videoHeight;

        // Capture the current frame from the video
        ctx.drawImage(videoRef.current, 0, 0, canvas.width, canvas.height);

        // Convert canvas image to Base64 format
        const imageData = canvas.toDataURL("image/jpeg");

        try {
            // Send image to backend for recognition
            const response = await axios.post("http://127.0.0.1:5000/recognize", { image: imageData });
            setResponseMessage(response.data.message); // Display response
        } catch (error) {
            console.error("Face recognition failed:", error);
            setResponseMessage("Recognition failed. Try again!");
        }
    };

    return (
        <div style={{ textAlign: "center", marginTop: "20px" }}>
            <video ref={videoRef} autoPlay playsInline style={{ width: "100%", borderRadius: "10px" }} />
            <canvas ref={canvasRef} style={{ display: "none" }} />
            <button onClick={captureImage} style={{ marginTop: "10px", padding: "10px 20px", cursor: "pointer" }}>
                Recognize Face
            </button>
            {responseMessage && <p>{responseMessage}</p>}
        </div>
    );
};

export default FaceRecognition;
