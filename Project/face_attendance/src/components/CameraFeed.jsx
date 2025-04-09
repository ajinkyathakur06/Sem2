import { useEffect, useRef, useState } from "react";

const CameraFeed = () => {
    const videoRef = useRef(null);
    const [deviceId, setDeviceId] = useState(null);
    const [devices, setDevices] = useState([]);

    useEffect(() => {
        const getCameras = async () => {
            try {
                const mediaDevices = await navigator.mediaDevices.enumerateDevices();
                const videoDevices = mediaDevices.filter(device => device.kind === "videoinput");
                setDevices(videoDevices);
                if (videoDevices.length > 0) {
                    setDeviceId(videoDevices[0].deviceId); // Set default to first camera
                }
            } catch (err) {
                console.error("Error fetching cameras: ", err);
            }
        };

        getCameras();
    }, []);

    useEffect(() => {
        const startCamera = async () => {
            if (!deviceId) return;
            try {
                const stream = await navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: deviceId } } });
                if (videoRef.current) {
                    videoRef.current.srcObject = stream;
                }
            } catch (err) {
                console.error("Error accessing webcam: ", err);
            }
        };

        startCamera();
    }, [deviceId]);

    return (
        <div>
            <h3>Select Camera:</h3>
            <select onChange={(e) => setDeviceId(e.target.value)} value={deviceId}>
                {devices.map((device, index) => (
                    <option key={index} value={device.deviceId}>
                        {device.label || `Camera ${index + 1}`}
                    </option>
                ))}
            </select>

            <video ref={videoRef} autoPlay playsInline style={{ width: "100%", borderRadius: "10px" }} />
        </div>
    );
};

export default CameraFeed;
