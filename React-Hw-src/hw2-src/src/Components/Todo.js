import React from 'react';
import { useState } from 'react';

function Todo({todo,todos,setTodos,handleEditTodos}) {
	
	const [edit,setEdit] = useState(false)
	const [editValue,setEditValue] = useState(todo.text)

	const handleFocus = (event) => {event.target.select()}

	const deleteHandler = () => {
		setTodos(todos.filter(el => el.id !== todo.id))
	}
	
	const completeHandler = () => {
		setTodos(todos.map((item) => {
			if(item.id === todo.id){
				return {
					...item, completed:!item.completed
				}
			}
			return item
		}))
	}

	const handleEdit = () => {
			setEdit(true)
	}

	const handleSave = (id) => {
		setEdit(false)
		if(!(/^ *$/.test(editValue))){
			handleEditTodos(editValue,id)
		}else{
			setEditValue(todo.text)
		}
	}

	const changeTodoText = (e) => {
		setEditValue(e.target.value)
	}

	if (edit) {
		return ( 
			<li>
				<form onSubmit={(e) => {
					e.preventDefault()
					handleSave(todo.id)
				}}>
					<label>
						<input type="text" className='new-todo' style={{padding:"0",margin:"0"}} value={editValue} name='editValue' onChange={changeTodoText} onFocus={handleFocus} autoFocus/>
					</label>
				</form>
			</li>		
		);
	} else {
		return (
			<li className={todo.completed ? "completed" : "active"}>
				<div className="view">	
					<input onClick={completeHandler} value={todo.text} className="toggle" checked={todo.completed} type="checkbox" />
					<label onClick={handleEdit}>{todo.text}</label>
					<button onClick={deleteHandler} className="destroy"></button>
				</div>
			</li>
		);
	}
}

export default Todo;