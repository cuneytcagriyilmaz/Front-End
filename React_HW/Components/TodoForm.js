import React from 'react';

function TodoForm({inputText,setInputText,todos,setTodos}) {

    const inputTextHandler = e => {
        setInputText(e.target.value)
    }

    const submitTodoHandler = e => {
        e.preventDefault()
        if(!(/^ *$/.test(inputText))) {
            setTodos([...todos,{text:inputText, completed:false, id:Date.now()}])
            setInputText("")
        }
    }

    return (
        <div>
            <header className="header">
                <h1>todos</h1>
                <form onSubmit={submitTodoHandler}>
                    <input className="new-todo" name='task' value={inputText} placeholder="What needs to be done?" autoFocus
                    onChange={inputTextHandler}/>
                </form>
            </header>
        </div>
    );
}

export default TodoForm;