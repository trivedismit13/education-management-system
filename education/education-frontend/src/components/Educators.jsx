import { useEffect, useState } from "react";

function Educators() {

  const [educators, setEducators] = useState([]);
  const [name, setName] = useState("");
  const [specialization, setSpecialization] = useState("");
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchEducators();
  }, []);

  const fetchEducators = async () => {
    const response = await fetch("http://localhost:8080/educators");
    const data = await response.json();
    setEducators(data);
  };

  const handleAddEducator = async () => {
    const response = await fetch("http://localhost:8080/educators", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        name,
        specialization
      })
    });

    const newEducator = await response.json();
    setEducators([...educators, newEducator]);

    resetForm();
  };

  const handleUpdateEducator = async () => {
    const response = await fetch(
      `http://localhost:8080/educators/${editingId}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name,
          specialization
        })
      }
    );

    const updatedEducator = await response.json();

    setEducators(
      educators.map(edu =>
        edu.id === editingId ? updatedEducator : edu
      )
    );

    resetForm();
  };

  const handleDeleteEducator = async (id) => {
    await fetch(`http://localhost:8080/educators/${id}`, {
      method: "DELETE"
    });

    setEducators(educators.filter(edu => edu.id !== id));
  };

  const handleEditEducator = (educator) => {
    setName(educator.name);
    setSpecialization(educator.specialization);
    setEditingId(educator.id);
  };

  const resetForm = () => {
    setName("");
    setSpecialization("");
    setEditingId(null);
  };

  return (
    <div>
      <h2>Educators</h2>

      <input
        type="text"
        placeholder="Educator Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <input
        type="text"
        placeholder="Specialization"
        value={specialization}
        onChange={(e) => setSpecialization(e.target.value)}
      />

      <button onClick={editingId ? handleUpdateEducator : handleAddEducator}>
        {editingId ? "Update Educator" : "Add Educator"}
      </button>

      <hr />

      {educators.map(edu => (
        <div key={edu.id}>
          {edu.name} - {edu.specialization}

          <button onClick={() => handleEditEducator(edu)}>
            Edit
          </button>

          <button onClick={() => handleDeleteEducator(edu.id)}>
            Delete
          </button>
        </div>
      ))}
    </div>
  );
}

export default Educators;
