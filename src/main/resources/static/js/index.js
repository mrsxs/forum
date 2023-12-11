var app = new Vue({
        el: '#app',
        data: {
            userData: {}, // ���ڴ洢�û�����
            post: [],
            comments: [],
            newPostData: {
                title: '',
                content: '',
            },

            showDialog: false,
            isExpanded: false
        },
        mounted: function () {
            axios.get('http://localhost:8080/users/isLogin').then((response) => {
                if (response.data.code === 20011) {
                } else {
                    //���userData
                    this.userData = {};
                }
            }).catch((error) => {
                console.log(error);
            });

        var storedUserData = localStorage.getItem('userData');
        if(storedUserData) {
            const data = JSON.parse(storedUserData);
            const currentTime = new Date().getTime();

            if (currentTime < data.expirationTime) {
                // ����δ���ڣ��������ø�userData
                this.userData = data.userData;
            } else {
                // �����ѹ��ڣ�ɾ����
                localStorage.removeItem('userData');
            }
        }

        // ��ȡ�����б�
        axios.get('http://localhost:8080/posts').then((response) => {
            if (response.data.code === 20011) {
                this.post = response.data.data;
            } else {
                this.$message.error('��ȡ����ʧ��');
            }
        }).catch((error) => {
            console.log(error);
        });
    },
    methods
:
{
    login: function () {
        // ��ת����¼ҳ��
        window.location.href = "login.html";
    }
,
    out: function () {
        axios.get('http://localhost:8080/users/logout', {}).then((response) => {
            if (response.data.code === 20011) {
                this.$message.error('ע���ɹ�');
                //ɾ�����ش洢���û�����
                localStorage.removeItem('userData');
                //ˢ��ҳ��
                window.location.reload();
            } else {
                this.$message.error('ע��ʧ��');
            }
        }).catch((error) => {
            this.$message.error('����ʧ�ܣ����Ժ�����');
            console.log(error);
        });
    }
,
    newPost: function () {
        // ���ȷ��ʵ�¼�ӿڣ�������ص�code��20011��˵���û��Ѿ���¼��������ת���½�����ҳ��
        axios.get('http://localhost:8080/users/isLogin').then((response) => {
            if (response.data.code === 20011) {
                app.showDialog = true
            } else {
                this.$message.error('���ȵ�¼');
            }
        }).catch((error) => {
            console.log(error);
        });
    }
,
    showDia: function () {
        this.showDia = false;
        // �������
        this.newPostData = {
            title: '',
            content: ''
        };
    }
,
    createPost: function () {
        //�����û�id ���� ����
        axios.post('http://localhost:8080/posts', {
            user: {
                id: this.userData.id
            },
            title: this.newPostData.title,
            content: this.newPostData.content
        }).then((response) => {
            if (response.data.code === 20041) {
                this.$message({message: '�����ɹ�', type: 'success'});
                //ˢ��ҳ��
                window.location.reload();
            } else {
                this.$message.error('����ʧ��');
            }
        }).catch((error) => {
            this.$message.error('����ʧ�ܣ����Ժ�����');
            console.log(error);
        });
    }
,
    Details: function (postId) {
        // �����URLӦ��ָ����������ҳURL
        // ��������ҳURL�� 'details.html' ����ʹ�ò�ѯ��������ID
        window.location.href = "details.html?id=" + postId;
    }
,
}
})
;