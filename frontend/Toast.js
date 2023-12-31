export class Toast {
    constructor(container, type, icon, title, message) {
        this.container = container;
        this.type = type;
        this.icon = icon;
        this.title = title;
        this.message = message;
        this.element = this.createToast();
        this.style();
        this.showUp();
        this.element.addEventListener("click", this);
        this.container.appendChild(this.element);
        this.autoRemove();
    }

    createToast() {
        const toast = document.createElement("div");
        const svg = document.createElement("div");
        const dataContainer = document.createElement("div");
        const title = document.createElement("h4");
        const toastBody = document.createElement("p");

        svg.innerHTML = this.icon;
        title.innerHTML = this.title;
        toastBody.innerHTML = this.message;

        svg.classList.add("icon");
        dataContainer.classList.add("message-container");

        dataContainer.appendChild(title);
        dataContainer.appendChild(toastBody);
        toast.appendChild(svg);
        toast.appendChild(dataContainer);
        return toast;
    }

    style() {
        this.element.classList.add("toast-box", "box");
        let root = document.documentElement;
        switch (this.type) {
            case "error":
                root.style.setProperty("--toast-bg-color", "#850b0b")
                root.style.setProperty("--toast-shadow-color", "#852e0b88")

                break;
            case "success":
                root.style.setProperty("--toast-bg-color", "#0b4085")
                root.style.setProperty("--toast-shadow-color", "#0b408588")

            default:
                break;
        }

    }

    showUp() {
        this.element.classList.add("active");
    }

    handleEvent(e) {
        switch (e.type) {
            case "click":
                this.dismiss();
                break;

            case "animationend":
                switch (e.animationName) {
                    case "toast-in":
                        this.timedRemoveToast();
                        break;
                    case "toast-out":
                        this.unPlug();
                        break;

                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    autoRemove() {
        this.element.addEventListener("animationend", this);
    }

    unPlug() {
        if (this.element) {
            this.element.removeEventListener("click", this);
            this.element.removeEventListener("animationend", this);
            this.container.removeChild(this.element);
            this.element = null;
        }
    }

    dismiss() {
        if (this.element) {
            this.element.classList.add("out");
            this.element.addEventListener("animationend", this);
        }
    }

    timedRemoveToast() {
        setTimeout(() => {
            this.dismiss();
        }, 2000);
    }
}