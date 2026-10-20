(function () {
  "use strict";

  const FORM_ENDPOINT = "https://formsubmit.co/ajax/olara@utzac.edu.mx";
  const CORREO = "olara@utzac.edu.mx";

  const hamburger = document.getElementById("hamburger");
  const menu = document.getElementById("menu");
  if (hamburger && menu) {
    hamburger.addEventListener("click", () => menu.classList.toggle("open"));
    menu.querySelectorAll("a").forEach((a) => {
      a.addEventListener("click", () => menu.classList.remove("open"));
    });
  }

  const year = document.getElementById("year");
  if (year) year.textContent = new Date().getFullYear();

  const form = document.getElementById("leadForm");
  const ok = document.getElementById("leadOk");
  const error = document.getElementById("leadError");
  const btn = document.getElementById("leadBtn");

  if (form) {
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      ok.hidden = true;
      error.hidden = true;

      const data = Object.fromEntries(new FormData(form).entries());
      if (!String(data.nombre || "").trim() ||
          !String(data.negocio || "").trim() ||
          !String(data.telefono || "").trim()) {
        error.textContent = "Completa nombre, negocio y teléfono.";
        error.hidden = false;
        return;
      }

      const original = btn.textContent;
      btn.disabled = true;
      btn.textContent = "Enviando…";

      try {
        const res = await fetch(FORM_ENDPOINT, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
          body: JSON.stringify({
            ...data,
            _subject: "Nuevo lead EasyVentas",
            _template: "table",
            _captcha: "false",
          }),
        });
        const json = await res.json().catch(() => ({}));
        const success = json.success === true || json.success === "true";
        if (!res.ok || !success) {
          throw new Error(json.message || "No se pudo enviar");
        }
        ok.hidden = false;
        form.reset();
        ok.scrollIntoView({ behavior: "smooth", block: "center" });
      } catch (err) {
        const texto =
          "Nombre: " + (data.nombre || "") +
          "\nNegocio: " + (data.negocio || "") +
          "\nTeléfono: " + (data.telefono || "") +
          "\nCorreo: " + (data.email || "") +
          "\nTipo: " + (data.tipo || "") +
          "\nMensaje: " + (data.mensaje || "");
        const mailto =
          "mailto:" + CORREO +
          "?subject=" + encodeURIComponent("Nuevo lead EasyVentas") +
          "&body=" + encodeURIComponent(texto);
        error.innerHTML =
          "No se pudo enviar automáticamente. " +
          '<a href="' + mailto + '">Enviar por correo</a> o escríbenos a ' +
          '<a href="mailto:' + CORREO + '">' + CORREO + "</a>.";
        error.hidden = false;
      } finally {
        btn.disabled = false;
        btn.textContent = original;
      }
    });
  }
})();
