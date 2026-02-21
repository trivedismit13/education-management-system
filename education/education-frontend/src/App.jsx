import Students from "./components/Students";
import Courses from "./components/Courses";
import Educators from "./components/Educators";
import "./App.css";

function App() {
  return (
    <div className="main-container">
      <h1 className="app-title">Education Management System</h1>

      <div className="section educators-section">
        <h2>👨‍🏫 Educators</h2>
        <Educators />
      </div>

      <div className="section courses-section">
        <h2>📚 Courses</h2>
        <Courses />
      </div>

      <div className="section students-section">
        <h2>👨‍🎓 Students</h2>
        <Students />
      </div>
    </div>
  );
}

export default App;