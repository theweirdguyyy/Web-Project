function submit(){
	localStorage.setItem("email",document.getElementById('mail').value);
	localStorage.setItem("password",document.getElementById('password').value);
	const json={
        mail:localStorage.getItem("email"),
        password:localStorage.getItem("password")
    };
	
	const xhr = new XMLHttpRequest();
    xhr.open("POST","http://localhost:8080/LearnEase/course");
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = () => {
        

    }
    console.log(json);
    xhr.send(JSON.stringify(json));
}