(function () {
  "use strict";

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

  if (window.location.hash) {
    const target = document.getElementById(window.location.hash.slice(1));
    if (target) {
      requestAnimationFrame(() => target.scrollIntoView({ block: "start" }));
    }
  }

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
        const res = await fetch("/api/leads", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data),
        });
        const json = await res.json().catch(() => ({}));
        if (!res.ok) throw new Error(json.error || "No se pudo enviar");
        ok.hidden = false;
        form.reset();
        ok.scrollIntoView({ behavior: "smooth", block: "center" });
      } catch (err) {
        error.textContent =
          "No se pudo enviar: " + err.message + ". Inténtalo de nuevo.";
        error.hidden = false;
      } finally {
        btn.disabled = false;
        btn.textContent = original;
      }
    });
  }
})();
