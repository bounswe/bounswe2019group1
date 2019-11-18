import emailvalidator from "email-validator";
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
  */
  return { valid: true, reason: "" };
}
