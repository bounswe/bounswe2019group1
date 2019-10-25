import emailvalidator from "email-validator";
export function validateCitizenshipNo(citizenship_number) {
  citizenship_number = citizenship_number.toString();
  var isEleven = /^[0-9]{11}$/.test(citizenship_number);
  var totalX = 0;
  for (var i = 0; i < 10; i++) {
    totalX += Number(citizenship_number.substr(i, 1));
  }
  var isRuleX = totalX % 10 === citizenship_number.substr(10, 1);
  var totalY1 = 0;
  var totalY2 = 0;
  for (i = 0; i < 10; i += 2) {
    totalY1 += Number(citizenship_number.substr(i, 1));
  }
  for (i = 1; i < 10; i += 2) {
    totalY2 += Number(citizenship_number.substr(i, 1));
  }
  var isRuleY =
    (totalY1 * 7 - totalY2) % 10 === citizenship_number.substr(9, 0);
  return isEleven && isRuleX && isRuleY;
}
export function validateIBAN(iban_number) {
  if (10000000000000000 > iban_number && iban_number >= 1000000000000000) {
    return false;
  } else {
    return true;
  }
}

export function validateBasicRegister(values) {
  if (values.username === "") {
    return { valid: false, reason: "Username cannot be empty." };
  } else if (values.pass === "") {
    return { valid: false, reason: "Password cannot be empty." };
  } else if (!emailvalidator.validate(values.email)) {
    return { valid: false, reason: "*Please enter a valid email." };
  } else if (values.name === "") {
    return { valid: false, reason: "Name cannot be empty." };
  } else if (values.surname === "") {
    return { valid: false, reason: "Surname cannot be empty." };
  } else if (values.location.address === "") {
    return { valid: false, reason: "Location cannot be empty." };
  }
  return { valid: true, reason: "" };
}

export function validateTraderRegister(values) {
  if (values.username === "") {
    return { valid: false, reason: "Username cannot be empty." };
  } else if (values.pass === "") {
    return { valid: false, reason: "Password cannot be empty." };
  } else if (!emailvalidator.validate(values.email)) {
    return { valid: false, reason: "*Please enter a valid email." };
  } else if (values.name === "") {
    return { valid: false, reason: "Name cannot be empty." };
  } else if (values.surname === "") {
    return { valid: false, reason: "Surname cannot be empty." };
  } else if (values.location.address === "") {
    return { valid: false, reason: "Location cannot be empty." };
  } /*else if (!validateIBAN(parseInt(values.iban))) {
    return {
      valid: false,
      reason: "Please enter a valid IBAN number(16 chars)."
    };
  } else if (!validateCitizenshipNo(parseInt(values.citizenshipno))){
    return { valid: false, reason: "Please enter a valid citizenship number." };
  }*/
  return { valid: true, reason: "" };
}
