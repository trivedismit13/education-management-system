import { useEffect, useState } from "react";

function Courses() {

  const [courses, setCourses] = useState([]);
  const [educators, setEducators] = useState([]);

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [maxCapacity, setMaxCapacity] = useState("");
  const [educatorId, setEducatorId] = useState("");

  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchCourses();
    fetchEducators();
  }, []);

  const fetchCourses = async () => {
    const response = await fetch("http://localhost:8080/courses");
    const data = await response.json();
    setCourses(data);
  };

  const fetchEducators = async () => {
    const response = await fetch("http://localhost:8080/educators");
    const data = await response.json();
    setEducators(data);
  };

  const handleAddCourse = async () => {
    const response = await fetch("http://localhost:8080/courses", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        title,
        description,
        maxCapacity: Number(maxCapacity),
        educatorId: educatorId ? Number(educatorId) : null
      })
    });

    const newCourse = await response.json();
    setCourses([...courses, newCourse]);
    resetForm();
  };

  const handleUpdateCourse = async () => {
    const response = await fetch(
      `http://localhost:8080/courses/${editingId}`,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          title,
          description,
          maxCapacity: Number(maxCapacity),
          educatorId: educatorId ? Number(educatorId) : null
        })
      }
    );

    const updatedCourse = await response.json();

    setCourses(
      courses.map(course =>
        course.id === editingId ? updatedCourse : course
      )
    );

    resetForm();
  };

  const handleDeleteCourse = async (id) => {
    await fetch(`http://localhost:8080/courses/${id}`, {
      method: "DELETE"
    });

    setCourses(courses.filter(course => course.id !== id));
  };

  const handleEditCourse = (course) => {
    setTitle(course.title);
    setDescription(course.description);
    setMaxCapacity(course.maxCapacity);
    setEducatorId(course.educator ? course.educator.id : "");
    setEditingId(course.id);
  };

  const resetForm = () => {
    setTitle("");
    setDescription("");
    setMaxCapacity("");
    setEducatorId("");
    setEditingId(null);
  };

  // Optional: View students in a course
  const handleViewStudents = async (courseId) => {
    const response = await fetch(
      `http://localhost:8080/courses/${courseId}/students`
    );

    const data = await response.json();
    alert(
      data.students.length === 0
        ? "No students enrolled"
        : data.students.map(s => s.name).join(", ")
    );
  };

  return (
    <div>
      <h2>Courses</h2>

      <input
        type="text"
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />

      <input
        type="text"
        placeholder="Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />

      <input
        type="number"
        placeholder="Max Capacity"
        value={maxCapacity}
        onChange={(e) => setMaxCapacity(e.target.value)}
      />

      <select
        value={educatorId}
        onChange={(e) => setEducatorId(e.target.value)}
      >
        <option value="">Select Educator</option>
        {educators.map(edu => (
          <option key={edu.id} value={edu.id}>
            {edu.name}
          </option>
        ))}
      </select>

      <button onClick={editingId ? handleUpdateCourse : handleAddCourse}>
        {editingId ? "Update Course" : "Add Course"}
      </button>

      <hr />

      {courses.map(course => (
        <div key={course.id}>
          <strong>{course.title}</strong> |
          {course.description} |
          Capacity: {course.maxCapacity} |
          Educator: {course.educator ? course.educator.name : "None"}

          <button onClick={() => handleEditCourse(course)}>Edit</button>
          <button onClick={() => handleDeleteCourse(course.id)}>Delete</button>
          <button onClick={() => handleViewStudents(course.id)}>
            View Students
          </button>
        </div>
      ))}
    </div>
  );
}

export default Courses;
