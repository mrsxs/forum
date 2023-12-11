var app = new Vue({
    data: {
        showLoginForm: true, // 用于控制显示登录表单还是注册表单
        username: '',
        password: '',
        regUsername: '',
        regPassword: '',
        password1: '',
        userData: {}
    },
    el: '#app',
    methods: {
        login: function () {
            axios.post('http://localhost:8080/users/login', {
                username: this.username.trim(),
                password: this.password.trim()
            }).then((response) => {
                if (response.data.code == 20011) {
                    this.$message({
                        message: '登录成功',
                        type: 'success'
                    });
                    this.userData = response.data.data;
                    const expirationTime = new Date().getTime() + 300 * 1000;
                    const dataToStore = {
                        userData: this.userData,
                        expirationTime: expirationTime
                    };
                    localStorage.setItem('userData', JSON.stringify(dataToStore));
                    //跳转 首页
                    window.location.href = "index.html";
                } else {
                    this.$message.error('登录失败，用户名或密码错误。');
                }
            }).catch((error) => {
                this.$message.error('登录请求失败，请检查网络连接。');
                console.log(error);
            });
        },
        register: function () {
            if (this.regPassword !== this.password1) {
                this.$message.error('两次输入的密码不一致');
                return;
            }
            axios.post('http://localhost:8080//users/add', {
                username: this.regUsername.trim(),
                password: this.regPassword.trim()
            }).then((response) => {
                if (response.data.code == 20041) {
                    this.$message({
                        message: '注册成功，请登录',
                        type: 'success'
                    });
                    //切换到登录表单
                    this.toggleForm('login');
                } else {
                    this.$message.error( response.data.msg);
                }
            }).catch((error) => {
                this.$message.error('注册请求失败，请检查网络连接。');
                console.log(error);
            });
        },
        toggleForm: function (formType) {
            if (formType === 'login') {
                this.showLoginForm = true;
            } else if (formType === 'register') {
                this.showLoginForm = false;
            }
        }
    }
});