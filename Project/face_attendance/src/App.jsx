import CameraFeed from "./components/CameraFeed";
import FaceRecognition from "./components/FaceRecognition";

function App() {
    return (
        <div style={{ textAlign: "center", padding: "20px" }}>
            <h1>Face Recognition Attendance</h1>
            <CameraFeed />
            <FaceRecognition />
        </div>
    );
}

export default App;
