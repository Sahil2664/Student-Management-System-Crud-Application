const API_BASE_URL = 'http://localhost:8080/api/students';

// DOM Elements
const form = document.getElementById('student-form');
const formTitle = document.getElementById('form-title');
const submitBtn = document.getElementById('submit-btn');
const cancelBtn = document.getElementById('cancel-btn');
const idInput = document.getElementById('student-id');
const nameInput = document.getElementById('name');
const ageInput = document.getElementById('age');
const departmentInput = document.getElementById('department');
const tbody = document.getElementById('student-tbody');
const messageBox = document.getElementById('message-box');

// State
let isEditing = false;

// Initialization
document.addEventListener('DOMContentLoaded', fetchStudents);

// Event Listeners
form.addEventListener('submit', handleFormSubmit);
cancelBtn.addEventListener('click', resetForm);

// Fetch all students
async function fetchStudents() {
    try {
        const response = await fetch(`${API_BASE_URL}/getAll`);
        if (!response.ok) throw new Error('Failed to fetch data');
        
        const students = await response.json();
        renderTable(students);
    } catch (error) {
        showMessage(error.message, 'error');
    }
}

// Render table rows
function renderTable(students) {
    tbody.innerHTML = '';
    
    if (students.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5" style="text-align:center">No students found. Add one!</td></tr>';
        return;
    }

    students.forEach(student => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>#${student.id}</td>
            <td><strong>${student.name}</strong></td>
            <td>${student.age}</td>
            <td><span style="background: rgba(79,70,229,0.2); padding: 4px 8px; border-radius: 12px; font-size: 0.85em; color: #818cf8;">${student.department}</span></td>
            <td>
                <button class="action-btn edit-btn" onclick="editStudent(${student.id}, '${student.name}', ${student.age}, '${student.department}')">Edit</button>
                <button class="action-btn delete-btn" onclick="deleteStudent(${student.id})">Delete</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

// Handle Form Submission
async function handleFormSubmit(e) {
    e.preventDefault();
    
    const payload = {
        name: nameInput.value,
        age: parseInt(ageInput.value),
        department: departmentInput.value
    };

    try {
        let response;
        if (isEditing) {
            response = await fetch(`${API_BASE_URL}/updateStudent/${idInput.value}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
        } else {
            response = await fetch(`${API_BASE_URL}/create`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
        }

        if (response.ok) {
            showMessage(isEditing ? 'Student updated successfully!' : 'Student added successfully!', 'success');
            resetForm();
            fetchStudents();
        } else {
            const errData = await response.json();
            showMessage(JSON.stringify(errData), 'error');
        }
    } catch (error) {
        showMessage('An error occurred. Make sure the backend is running.', 'error');
    }
}

// Set form to edit mode
function editStudent(id, name, age, department) {
    isEditing = true;
    formTitle.textContent = 'Edit Student';
    submitBtn.textContent = 'Update Student';
    cancelBtn.classList.remove('hidden');
    
    idInput.value = id;
    nameInput.value = name;
    ageInput.value = age;
    departmentInput.value = department;
    
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

// Delete a student
async function deleteStudent(id) {
    if (!confirm('Are you sure you want to delete this student?')) return;
    
    try {
        const response = await fetch(`${API_BASE_URL}/deleteStudent/${id}`, {
            method: 'DELETE'
        });
        
        if (response.ok) {
            showMessage('Student deleted successfully!', 'success');
            fetchStudents();
        } else {
            throw new Error('Failed to delete student');
        }
    } catch (error) {
        showMessage(error.message, 'error');
    }
}

// Reset form
function resetForm() {
    isEditing = false;
    form.reset();
    idInput.value = '';
    formTitle.textContent = 'Add New Student';
    submitBtn.textContent = 'Save Student';
    cancelBtn.classList.add('hidden');
}

// Show feedback messages
function showMessage(msg, type) {
    messageBox.textContent = msg;
    messageBox.className = `message-box ${type}`;
    messageBox.classList.remove('hidden');
    
    setTimeout(() => {
        messageBox.classList.add('hidden');
    }, 4000);
}
