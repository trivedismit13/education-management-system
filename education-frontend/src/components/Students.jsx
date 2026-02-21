import { useEffect, useState } from "react";

function Students() {

  const [students, setStudents] = useState([]);
  const [courses, setCourses] = useState([]);

  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [selectedCourseId, setSelectedCourseId] = useState("");

  useEffect(() => {
    fetchStudents();
    fetchCourses();
  }, []);

  const fetchStudents = async () => {
    const response = await fetch("http://localhost:8080/students");
    const data = await response.json();
    setStudents(data);
  };

  const fetchCourses = async () => {
    const response = await fetch("http://localhost:8080/courses");
    const data = await response.json();
    setCourses(data);
  };

  const handleAddStudent = async () => {
    const response = await fetch("http://localhost:8080/students", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, age: Number(age) })
    });

    const newStudent = await response.json();
    setStudents([...students, newStudent]);
    resetForm();
  };

  const handleUpdateStudent = async () => {
    const response = await fetch(
      `http://localhost:8080/students/${editingId}`,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, age: Number(age) })
      }
    );

    const updatedStudent = await response.json();

    setStudents(
      students.map(student =>
        student.id === editingId ? updatedStudent : student
      )
    );

    resetForm();
  };

  const handleDeleteStudent = async (id) => {
    await fetch(`http://localhost:8080/students/${id}`, {
      method: "DELETE"
    });

    setStudents(students.filter(student => student.id !== id));
  };

  const handleEditStudent = (student) => {
    setName(student.name);
    setAge(student.age);
    setEditingId(student.id);
  };

  const handleEnroll = async (studentId) => {
    if (!selectedCourseId) {
      alert("Select a course first");
      return;
    }

    await fetch(
      `http://localhost:8080/students/${studentId}/enroll/${selectedCourseId}`,
      { method: "POST" }
    );

    alert("Student enrolled successfully!");
  };

  const handleDrop = async (studentId) => {
    if (!selectedCourseId) {
      alert("Select a course first");
      return;
    }

    await fetch(
      `http://localhost:8080/students/${studentId}/drop/${selectedCourseId}`,
      { method: "DELETE" }
    );

    alert("Course dropped successfully!");
  };

  const handleViewCourses = async (studentId) => {
    const response = await fetch(
      `http://localhost:8080/students/${studentId}/courses`
    );

    const data = await response.json();

    if (data.length === 0) {
      alert("No courses enrolled");
    } else {
      alert(data.map(course => course.title).join(", "));
    }
  };

  const resetForm = () => {
    setName("");
    setAge("");
    setEditingId(null);
  };

  return (
    <div>
      <h2>Students</h2>

      <input
        type="text"
        placeholder="Enter name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <input
        type="number"
        placeholder="Enter age"
        value={age}
        onChange={(e) => setAge(e.target.value)}
      />

      <button onClick={editingId ? handleUpdateStudent : handleAddStudent}>
        {editingId ? "Update Student" : "Add Student"}
      </button>

      <hr />

      {students.map(student => (
        <div key={student.id} className="card"style={{ marginBottom: "10px" }}>
          <strong>{student.name}</strong> - Age: {student.age}

          <br />

          <select
            value={selectedCourseId}
            onChange={(e) => setSelectedCourseId(e.target.value)}
          >
            <option value="">Select Course</option>
            {courses.map(course => (
              <option key={course.id} value={course.id}>
                {course.title}
              </option>
            ))}
          </select>

          <button onClick={() => handleEnroll(student.id)}>
            Enroll
          </button>

          <button onClick={() => handleDrop(student.id)}>
            Drop
          </button>

          <button onClick={() => handleViewCourses(student.id)}>
            View Courses
          </button>

          <button onClick={() => handleEditStudent(student)}>
            Edit
          </button>

          <button className="delete" onClick={() => handleDeleteStudent(student.id)}>
            Delete
          </button>
        </div>
      ))}
    </div>
  );
}

export default Students;
