// 이메일 유효성 검사
function validateEmail(email) {
  const regex =
    /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
  return regex.test(email);
}

// 이름 유효성 검사
function validateName(name) {
  const regex = /^[가-힣a-zA-Z]+$/;
  return regex.test(name);
}

// 비밀번호 유효성 검사
function validatePwd(password) {
  const regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
  return regex.test(password);
}

export { validateEmail, validateName, validatePwd };
