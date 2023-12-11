var app = new Vue({
    data: {
        showLoginForm: true, // ���ڿ�����ʾ��¼������ע���
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
                        message: '��¼�ɹ�',
                        type: 'success'
                    });
                    this.userData = response.data.data;
                    const expirationTime = new Date().getTime() + 300 * 1000;
                    const dataToStore = {
                        userData: this.userData,
                        expirationTime: expirationTime
                    };
                    localStorage.setItem('userData', JSON.stringify(dataToStore));
                    //��ת ��ҳ
                    window.location.href = "index.html";
                } else {
                    this.$message.error('��¼ʧ�ܣ��û������������');
                }
            }).catch((error) => {
                this.$message.error('��¼����ʧ�ܣ������������ӡ�');
                console.log(error);
            });
        },
        register: function () {
            if (this.regPassword !== this.password1) {
                this.$message.error('������������벻һ��');
                return;
            }
            axios.post('http://localhost:8080//users/add', {
                username: this.regUsername.trim(),
                password: this.regPassword.trim()
            }).then((response) => {
                if (response.data.code == 20041) {
                    this.$message({
                        message: 'ע��ɹ������¼',
                        type: 'success'
                    });
                    //�л�����¼��
                    this.toggleForm('login');
                } else {
                    this.$message.error( response.data.msg);
                }
            }).catch((error) => {
                this.$message.error('ע������ʧ�ܣ������������ӡ�');
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