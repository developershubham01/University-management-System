// Simple frontend for University Management System
const API_BASE = 'http://localhost:8080';

// Navigation
document.querySelectorAll('.nav-buttons button').forEach(btn=>{
  btn.addEventListener('click', ()=> showView(btn.dataset.view));
});
function showView(viewId){
  document.querySelectorAll('.view').forEach(v=> v.classList.remove('active'));
  const el = document.getElementById(viewId);
  if(el) el.classList.add('active');
  log('Switched to '+viewId);
}

// Helpers
function log(msg){ document.getElementById('log').innerText = msg; setTimeout(()=>document.getElementById('log').innerText='',3000); }
function apiFetch(path, opts={}){
  opts.headers = opts.headers || {'Content-Type':'application/json'};
  return fetch(API_BASE + path, opts).then(async res=>{
    const text = await res.text();
    try{ const json = JSON.parse(text); return {ok:res.ok, status:res.status, data:json}; }
    catch(e){ return {ok:res.ok, status:res.status, data:text}; }
  }).catch(e=>{ return {ok:false, status:0, data: e.message}; });
}
function renderTable(containerId, data) {
  const el = document.getElementById(containerId);

  if (!data) {
    el.innerHTML = "<p>No data found.</p>";
    return;
  }

  if (!Array.isArray(data)) data = [data];

  let html = `
    <table class="nice-table">
      <tr>
        ${Object.keys(data[0]).map(k => `<th>${k}</th>`).join("")}
      </tr>
      ${data.map(row => `
        <tr>
          ${Object.values(row).map(v => `<td>${v}</td>`).join("")}
        </tr>
      `).join("")}
    </table>
  `;

  el.innerHTML = html;
}

// Students
async function addStudent(){
  const f = document.getElementById('studentForm');
  const data = {
    name: f.name.value,
    email: f.email.value,
    department: f.department.value,
    age: f.age.value ? parseInt(f.age.value) : null
  };
  const r = await apiFetch('/addstudent', {method:'POST', body: JSON.stringify(data)});
  document.getElementById('studentsResult').innerText = JSON.stringify(r);
  log('Add student → ' + r.status);
  f.reset();
}

async function getStudentById(){
  const id = document.getElementById('getStudentId').value.trim();
  if(!id) return alert('Enter ID');
  const r = await apiFetch(`/getstudent/id/${id}`);
  document.getElementById('studentsResult').innerText = JSON.stringify(r, null, 2);
  log('Get student by id → ' + r.status);
}

async function getStudentByName(){
  const name = document.getElementById('getStudentName').value.trim();
  if(!name) return alert('Enter name');
  const r = await apiFetch(`/getstudent/name/${encodeURIComponent(name)}`);
  document.getElementById('studentsResult').innerText = JSON.stringify(r, null, 2);
  log('Get student by name → ' + r.status);
}

// Professors
async function addProfessor(){
  const f = document.getElementById('profForm');
  const data = { name: f.name.value, email: f.email.value, department: f.department.value };
  const r = await apiFetch('/addprofessor', {method:'POST', body: JSON.stringify(data)});
  document.getElementById('professorResult').innerText = JSON.stringify(r);
  log('Add professor → ' + r.status);
  f.reset();
}

async function getProfessorById(){
  const id = document.getElementById('getProfId').value.trim();
  if(!id) return alert('Enter ID');
  const r = await apiFetch(`/getprofessor/id/${id}`);
  document.getElementById('professorResult').innerText = JSON.stringify(r, null, 2);
  log('Get professor by id → ' + r.status);
}

async function getProfessorByDept(){
  const dept = document.getElementById('getProfDept').value.trim();
  if(!dept) return alert('Enter department');
  const r = await apiFetch(`/getprof/dept/${encodeURIComponent(dept)}`);
  document.getElementById('professorResult').innerText = JSON.stringify(r, null, 2);
  log('Get professor by dept → ' + r.status);
}

// Courses
async function addCourse(){
  const f = document.getElementById('courseForm');
  const data = { 
      title: f.title.value,
      description: f.description.value,
      credit: parseInt(f.credit.value) };
  const profId = f.professorId.value;
  if(!profId) return alert('Professor ID required');
  const r = await apiFetch(`/addcourse/${profId}`, {method:'POST', body: JSON.stringify(data)});
  document.getElementById('courseResult').innerText = JSON.stringify(r);
  log('Add course → ' + r.status);
  f.reset();
}

async function assignStudentToCourse(){
  const sId = document.getElementById('assignStudentId').value.trim();
  const cId = document.getElementById('assignCourseId').value.trim();
  if(!sId || !cId) return alert('Enter both IDs');
  const r = await apiFetch(`/assign/${sId}/${cId}`);
  document.getElementById('courseResult').innerText = JSON.stringify(r, null, 2);
  log('Assign student → ' + r.status);
}

// Assignments
async function addAssignment(){
  const f = document.getElementById('assignmentForm');
  const data = { title: f.title.value, description: f.description.value, dueDate: f.dueDate.value || null };
  const courseId = f.courseId.value;
  if(!courseId) return alert('Course ID required');
  const r = await apiFetch(`/addassignment/${courseId}`, {method:'POST', body: JSON.stringify(data)});
  document.getElementById('assignmentResult').innerText = JSON.stringify(r);
  log('Add assignment → ' + r.status);
  f.reset();
}

async function getAssignmentById(){
  const id = document.getElementById('getAssignmentId').value.trim();
  if(!id) return alert('Enter ID');
  const r = await apiFetch(`/getassignment/${id}`);
  document.getElementById('assignmentResult').innerText = JSON.stringify(r, null, 2);
  log('Get assignment → ' + r.status);
}

// Submissions
async function addSubmission(){
  const f = document.getElementById('submissionForm');
  const data = { content: f.content.value };
  const assignmentId = f.assignmentId.value;
  const studentId = f.studentId.value;
  if(!assignmentId || !studentId) return alert('Provide assignment and student IDs');
  const r = await apiFetch(`/addsubmission/${assignmentId}/${studentId}`, {method:'POST', body: JSON.stringify(data)});
  document.getElementById('submissionResult').innerText = JSON.stringify(r);
  log('Add submission → ' + r.status);
  f.reset();
}

async function getSubmissionById(){
  const id = document.getElementById('getSubmissionId').value.trim();
  if(!id) return alert('Enter ID');
  const r = await apiFetch(`/getsubmission/${id}`);
  document.getElementById('submissionResult').innerText = JSON.stringify(r, null, 2);
  log('Get submission → ' + r.status);
}

// initial view
showView('dashboard');
