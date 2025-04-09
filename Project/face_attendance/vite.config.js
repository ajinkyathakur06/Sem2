import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import fs from "fs";

export default defineConfig({
  plugins: [react()],
  server: {
    host: "face-attendance.local",
    port: 5173,
    https: {
      key: fs.readFileSync("face-attendance.local-key.pem"),
      cert: fs.readFileSync("face-attendance.local.pem"),
    },
  },
});
