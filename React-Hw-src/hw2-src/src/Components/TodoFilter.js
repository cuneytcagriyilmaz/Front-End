import React from 'react';

function TodoFilter({status,todos,setTodos,setStatus,itemsLength}) {

    const handlerStatus = (e) => {
        setStatus(e.target.innerHTML)
    }

    const clearCompleted = () => {
        setTodos(todos.filter(todo => todo.completed !== true))
    }

    return (
        <footer className="footer">
            <span className="todo-count">
                <strong>{itemsLength === 1 ? (itemsLength + " item left") : (itemsLength + " items left")}</strong>
            </span>
            <ul className="filters">
                <li>
                    <a href='#/' className={status === "All" ? "selected" : ""} onClick={handlerStatus}>All</a>
                </li>
                <li>
                    <a href='#/' className={status === "Active" ? "selected" : ""} onClick={handlerStatus}>Active</a>
                </li>
                <li>
                    <a href='#/' className={status === "Completed" ? "selected" : ""} onClick={handlerStatus}>Completed</a>
                </li>
            </ul>
            <button onClick={clearCompleted} className="clear-completed">
                Clear completed
            </button>
        </footer>
    )
}

export default TodoFilter;